package com.conquer.service;

import com.conquer.dto.RelatorioCidadeGastoDTO;
import com.conquer.dto.RelatorioCidadeGastoDetalheDTO;
import com.conquer.model.Cidade;
import com.conquer.model.CidadeGasto;
import com.conquer.model.ExtratoCartao;
import com.conquer.repository.CidadeGastoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RelatorioCidadeGastoServiceImpl implements RelatorioCidadeGastoService {

    public static Logger logger=Logger.getLogger("RelatorioCidadeGastoService");

    @Autowired
    private CidadeGastoRepository repository;
    @Autowired
    private Environment env;

    public RelatorioCidadeGastoDTO getRelatorioCidadeGasto(String dataInicial, String dataFinal) throws ParseException {
        RelatorioCidadeGastoDTO relatorio = new RelatorioCidadeGastoDTO();
        relatorio.setDataInicial(dataInicial);
        relatorio.setDataFinal(dataFinal);

        try {
            List<ExtratoCartao> listaExtratoCartao = getListaExtratoCartao(dataInicial, dataFinal);

            //Apenas para testes
//            ExtratoCartao e = new ExtratoCartao();
//            e.setValorTransacao("1.000,00");
//            e.setDataTransacao("10/01/2020");
//            e.setId("12311231");
//            ExtratoCartaoEstabelecimento ee = new ExtratoCartaoEstabelecimento();
//            ExtratoCartaoMunicipio m = new ExtratoCartaoMunicipio();
//            m.setCodigoIBGE("1302603");
//            ee.setMunicipio(m);
//            e.setEstabelecimento(ee);
//            List<ExtratoCartao> listaExtratoCartao = Collections.singletonList(e);

            List<CidadeGasto> listaCidadeGasto = persistirExtrato(listaExtratoCartao);

            Map<Cidade, Double> groupByCidade =
                    listaCidadeGasto.stream().collect(
                            Collectors.groupingBy(CidadeGasto::getCidade, Collectors.summingDouble(CidadeGasto::getValor)));

            List<RelatorioCidadeGastoDetalheDTO> cidades = new ArrayList<RelatorioCidadeGastoDetalheDTO>();
            groupByCidade.forEach((k,v) -> cidades.add(new RelatorioCidadeGastoDetalheDTO(k.getNome(),v)));

            relatorio.setCidades(cidades);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return relatorio;
    }

    /**
     * Persiste em banco a lista retornada da API
     * @param listaExtratoCartao : List<ExtratoCartao>
     * @return List<CidadeGasto>
     * @throws ParseException
     */
    private List<CidadeGasto> persistirExtrato(List<ExtratoCartao> listaExtratoCartao) throws ParseException {
        List<CidadeGasto> listaCidadeGasto = new ArrayList<CidadeGasto>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (ExtratoCartao extrato : listaExtratoCartao) {
            Double valor = Double.valueOf(extrato.getValorTransacao()
                    .replace(".","")
                    .replace(",","."));

            CidadeGasto cidadeGasto = new CidadeGasto(
                    Long.valueOf(extrato.getId()),
                    sdf.parse(extrato.getDataTransacao()),
                    new Cidade(
                            Long.valueOf(extrato.getEstabelecimento().getMunicipio().getCodigoIBGE()),
                            extrato.getEstabelecimento().getMunicipio().getNomeIBGE()
                    ),
                    valor
            );
            repository.save(cidadeGasto);
            listaCidadeGasto.add(cidadeGasto);
        }
        return listaCidadeGasto;
    }

    /**
     * Retorna a lista de "lançamentos" de acordo com o intevalo informado
     * @param dataInicial : String
     * @param dataFinal : String
     * @return List<ExtratoCartao>
     */
    private List<ExtratoCartao> getListaExtratoCartao(String dataInicial, String dataFinal) {
        List<ExtratoCartao> listaExtratoCartao = new ArrayList<ExtratoCartao>();
        RestTemplate restTemplate  = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("chave-api-dados", env.getProperty("api.key"));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ObjectMapper mapper = new ObjectMapper();

        Boolean aindaHaRegistros = true;
        Integer pagina = 1;
        String urlBase = env.getProperty("api.url");

        while(aindaHaRegistros) {
            String url = urlBase
                    + "?dataTransacaoInicio=" + dataInicial
                    + "&dataTransacaoFim=" + dataFinal
                    +"&pagina=" + pagina;

            try {
                logger.info("Exchange: HEADERS: " + entity.toString() + " URL:" + url);
                ResponseEntity<String> extratoCartaoRE = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                ExtratoCartao[] arrayExtratoCartao = mapper.readValue(extratoCartaoRE.getBody(), ExtratoCartao[].class);
                listaExtratoCartao.addAll(Arrays.asList(arrayExtratoCartao));
                pagina ++;
                if (arrayExtratoCartao.length != 15) {
                    aindaHaRegistros = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaExtratoCartao;
    }
}
