package com.astronout.testmagangdot.api;

import com.astronout.testmagangdot.model.Malang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("Gallery_Malang_Batu.json")
    Call<List<Malang>> getMalang();

}
