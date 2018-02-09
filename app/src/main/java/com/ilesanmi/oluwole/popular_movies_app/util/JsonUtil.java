package com.ilesanmi.oluwole.popular_movies_app.util;


import com.ilesanmi.oluwole.popular_movies_app.MyParcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtil {

    private static final MyParcelable sMyParcelable = new MyParcelable();
    private static JSONObject jsonObject;
    private static JSONArray jsonArray;
    private static Integer  jsonArrayIndex;

    /**
     * Class passes data to a parcelable object.
     * kindly note that class simply uses getters and setters it does not use the parcel functionalities of parcelable object .
     */

    public static MyParcelable parseMoviesToParcelableObject(String json, int arrayIterationIndex, int flag) throws JSONException {
        jsonArrayIndex = arrayIterationIndex;

        jsonObject = new JSONObject(json);
        setJsonArray(jsonObject);
        setJsonObject(jsonArray);


        deserializeTitle();
        deserializePosterPath();
        deserializeVoteAverage();
        deserializeOverview();
        deserializeReleaseDate();

        deserializeFlag(flag);

        return sMyParcelable;
    }

    private static void setJsonObject(JSONArray array) throws JSONException {
        jsonObject = jsonArray.getJSONObject(jsonArrayIndex);
    }

    private static void setJsonArray(JSONObject obj) throws JSONException {
        jsonArray = jsonObject.getJSONArray("results");
    }


    private static void deserializeTitle() throws JSONException {
        sMyParcelable.setTitle(jsonObject.getString("title"));
    }

    private static void deserializePosterPath() throws JSONException {
        sMyParcelable.setPosterPath(jsonObject.getString("poster_path"));
    }

    private static void deserializeVoteAverage() throws JSONException {
        sMyParcelable.setVoteAverage(jsonObject.getDouble("vote_average"));
    }

    private static void deserializeOverview() throws JSONException {
        sMyParcelable.setOverview(jsonObject.getString("overview"));
    }

    private static void deserializeReleaseDate() throws JSONException {
        sMyParcelable.setReleaseDate(jsonObject.getString("release_date"));

    }

    private static void deserializeFlag(int flag) {
        sMyParcelable.setFlag(flag);

    }






}


