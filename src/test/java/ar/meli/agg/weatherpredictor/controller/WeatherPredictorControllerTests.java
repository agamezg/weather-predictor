package ar.meli.agg.weatherpredictor.controller;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.service.WPService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPredictorControllerTests {

    private MockMvc mvc;

    private JacksonTester<WeatherPrediction> weatherJson;

    @Mock
    private WPService wpService;

    @InjectMocks
    private WPController topicController;

    @Before
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(topicController)
                .build();
    }

    @Test
    public void getWeather() throws Exception {
        given(wpService.find(566))
                .willReturn(aRainyDay());

        MockHttpServletResponse response = mvc.perform(
                get("/wp-service/weather?day=566")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(weatherJson.write(aRainyDay()).getJson());
    }

    @Test
    public void getWeather2() throws Exception {
        given(wpService.find(200))
                .willReturn(aBeautifulDay());

        MockHttpServletResponse response = mvc.perform(
                get("/wp-service/weather?day=200")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(weatherJson.write(aBeautifulDay()).getJson());
    }

    private WeatherPrediction aRainyDay() {
        return new WeatherPrediction(566, Weather.RAIN);
    }

    private WeatherPrediction aBeautifulDay() {
        return new WeatherPrediction(200, Weather.BEAUTIFUL_DAY);
    }
    
    
}
