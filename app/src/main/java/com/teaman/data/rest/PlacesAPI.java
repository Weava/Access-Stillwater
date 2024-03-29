package com.teaman.data.rest;

import com.google.android.gms.location.places.PlacePhotoResult;
import com.teaman.data.entities.json.Results;
import com.teaman.data.entities.json.places.PlaceEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by weava on 3/13/16.
 */
public interface PlacesApi
{
    static final String API_KEY = "<places-api-key-here>";

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishments(
            @Query("location") String latLong,
            @Query("radius") float radius
    );

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishmentsWithType(
            @Query("location") String latLong,
            @Query("radius") float radius,
            @Query("type") String type
    );

    @GET("nearbysearch/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllNearbyEstablishmentsWithTypeAndName(
            @Query("location") String latLong,
            @Query("radius") float radius,
            @Query("type") String type,
            @Query("name") String name
    );


    @GET("details/json?key=" + API_KEY)
    Call<Results<PlaceEntity>> getAllDetails(
            @Query("placeid") String id
    );

    ///place/photo?photoreference=PHOTO_REFERENCE&sensor=false&maxheight=MAX_HEIGHT&maxwidth=MAX_WIDTH&key=YOUR_API_KEY
    @GET("photo?key=" + API_KEY)
    Call<PlacePhotoResult> getPhotoForEstablishment(
            @Query("photoreference") String reference,
            @Query("maxwidth") int width
    );

}
