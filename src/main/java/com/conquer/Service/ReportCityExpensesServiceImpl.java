package com.conquer.service;

import com.conquer.dto.ReportCityExpensesDTO;
import com.conquer.dto.ReportCityExpensesDetailDTO;
import com.conquer.model.*;
import com.conquer.repository.CityExpensesRepository;
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
public class ReportCityExpensesServiceImpl implements ReportCityExpensesService {

    public static final Logger logger=Logger.getLogger("RelatorioCidadeGastoService");

    private final SimpleDateFormat SDF_DD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private CityExpensesRepository repository;

    @Autowired
    private Environment env;

    public ReportCityExpensesDTO getReportCityExpenses(String startDate, String endDate) {
        return ReportCityExpensesDTO.builder()
                .startDate(startDate)
                .endDate(endDate)
                .cities(getReportCityExpensesDetailDTOS())
                .build();
    }

    public List<ReportCityExpensesDetailDTO> getReportCityExpensesDetailDTOS() {
        List<CityExpenses> listCityExpenses = persistExtract(getExtractCardsList());

        Map<City, Double> groupByCity =
                listCityExpenses.stream().collect(
                        Collectors.groupingBy(CityExpenses::getCity, Collectors.summingDouble(CityExpenses::getValue)));

        List<ReportCityExpensesDetailDTO> cities = new ArrayList<>();
        groupByCity.forEach((k,v) -> cities.add(new ReportCityExpensesDetailDTO(k.getName(),v)));
        return cities;
    }

    public List<ExtractCard> getExtractCardsList() {
        return Collections.singletonList(getExtractCard());
    }

    public ExtractCard getExtractCard() {
        return ExtractCard.builder()
                .value("1.000,0021213213")
                .date("10/01/2020")
                .id("12311231")
                .value("1.000,0021213213")
                .place(ExtractCardPlace.builder()
                        .city(ExtractCardCity.builder()
                                .ibgeCode("1302603")
                                .ibgeName("CURITIBA")
                                .build())
                        .build())
                .build();
    }

    public List<CityExpenses> persistExtract(List<ExtractCard> listExtractCard)  {
        List<CityExpenses> listaCityExpenses = new ArrayList<>();

        listExtractCard.forEach(extract -> listaCityExpenses.add(persistCityExpenses(extract)));

        return listaCityExpenses;
    }

    public CityExpenses persistCityExpenses(ExtractCard extract) {
        CityExpenses cityExpenses = null;
        try {
            cityExpenses = CityExpenses.builder()
                    .id(Long.valueOf(extract.getId()))
                    .date(SDF_DD_MM_YYYY.parse(extract.getDate()))
                    .city(City.builder()
                            .id(Long.valueOf(extract.getPlace().getCity().getIbgeCode()))
                            .name(extract.getPlace().getCity().getIbgeName())
                            .build())
                    .value(formatValue(extract.getValue()))
                    .build();
            repository.save(cityExpenses);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cityExpenses;
    }

    public Double formatValue(String value) {
        return Double.valueOf(value
                .replace(".", "")
                .replace(",", "."));
    }

    //TODO
    private List<ExtractCard> getListExtractCard(String startDate, String endDate) {
        List<ExtractCard> listExtractCard = new ArrayList<>();
        RestTemplate restTemplate  = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("chave-api-dados", env.getProperty("api.key"));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ObjectMapper mapper = new ObjectMapper();

        boolean hasLines = true;
        int page = 1;
        String urlBase = env.getProperty("api.url");

        while(hasLines) {
            String url = urlBase
                    + "?dataTransacaoInicio=" + startDate
                    + "&dataTransacaoFim=" + endDate
                    +"&pagina=" + page;

            try {
                logger.info("Exchange: HEADERS: " + entity.toString() + " URL:" + url);
                ResponseEntity<String> extratoCartaoRE = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                ExtractCard[] arrayExtractCard = mapper.readValue(extratoCartaoRE.getBody(), ExtractCard[].class);
                listExtractCard.addAll(Arrays.asList(arrayExtractCard));
                page++;
                if (arrayExtractCard.length != 15) {
                    hasLines = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listExtractCard;
    }
}
