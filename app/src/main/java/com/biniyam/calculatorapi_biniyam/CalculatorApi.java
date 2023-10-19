package com.biniyam.calculatorapi_biniyam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CalculatorApi {
    @POST("calculate")
    Call<Double> performCalculation(@Body CalculationRequest request);
}
