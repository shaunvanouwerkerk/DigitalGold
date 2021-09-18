package com.example.digital_gold.controller;

import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.PortfolioOverviewService;
import com.example.digital_gold.service.PortfolioValueOverview;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PortfolioOverviewControllerTest {

    @Mock
    private PortfolioOverviewService portfolioOverviewService;

    @Mock
    private AuthenticatorService authenticatorService;

    @BeforeEach
    public void initRestAssuredMockStandAlone() {
        this.portfolioOverviewService = Mockito.mock(PortfolioOverviewService.class);
        this.authenticatorService = Mockito.mock(AuthenticatorService.class);
        RestAssuredMockMvc.standaloneSetup(new PortfolioOverviewController(portfolioOverviewService, authenticatorService));
    }

    @Test
    public void portfolioToday_authorization_header_not_present_400() {
        when()
                .get("/portfoliovalueoverviewtoday")
                .then()
                .statusCode(400);
    }

    @Test
    public void portfolioToday_authorization_header_present_Unauthenticated_401() {
        String uuid = "c6909b68-16af-11ec-9621-0242ac130002";
        given()
                .header("Authorization", uuid).
                when()
                .get("/portfoliovalueoverviewtoday")
                .then()
                .statusCode(401);
    }

    @Test
    public void portfolioToday_authorization_header_present_token_correct_format_authenticated_200() {
        String uuid = "0a461378-16b1-11ec-9621-0242ac130002";
        Mockito.when(authenticatorService.authenticateUsername(uuid)).thenReturn("username");
        given()
                .header("Authorization", uuid).
        when()
                .get("/portfoliovalueoverviewtoday")
                .then()
                .statusCode(200);
    }

    @Test
    public void portfolioToday_returns_content_type_application_json() {
        String uuid = "0a461378-16b1-11ec-9621-0242ac130002";
        Mockito.when(authenticatorService.authenticateUsername(uuid)).thenReturn("username");
        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(),1);
        Mockito.when(portfolioOverviewService.getPortfolioOverviewToday(authenticatorService.
                        authenticateUsername(uuid))).thenReturn(expected);
        given()
                .header("Authorization",uuid).
        when()
                .get("/portfoliovalueoverviewtoday")
        .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    public void portfolioToday_object_with_content() {
        String uuid = "0a461378-16b1-11ec-9621-0242ac130002";
        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(),1);
        Mockito.when(authenticatorService.authenticateUsername(uuid)).thenReturn("username");
        Mockito.when(portfolioOverviewService.getPortfolioOverviewToday("username")).thenReturn(expected);
        PortfolioValueOverview actual =
        given()
                .header("Authorization", uuid).
        when()
                .get("/portfoliovalueoverviewtoday")
                .then()
                .extract()
                .body()
                .as(PortfolioValueOverview.class);

            assertThat(actual).isEqualTo(expected);
    }
}
