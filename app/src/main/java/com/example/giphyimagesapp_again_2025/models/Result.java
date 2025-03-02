package com.example.giphyimagesapp_again_2025.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class Result {

//    @SerializedName("meta")
//    @Expose
//    private JSONObject meta;
//
//    @SerializedName("pagination")
//    @Expose
//    private JSONObject pagination;

    @SerializedName("data")
    @Expose
    private List<ImageM> results = null;

    public Result() {
    } //to prevent any possible null point exceptions

    public List<ImageM> getResults() {
        return results;
    }

    public void setResults(List<ImageM> results) {
        this.results = results;
    }

//    public JSONObject getMeta() {
//        return meta;
//    }
//
//    public void setMeta(JSONObject meta) {
//        this.meta = meta;
//    }
//
//    public JSONObject getPagination() {
//        return pagination;
//    }
//
//    public void setPagination(JSONObject pagination) {
//        this.pagination = pagination;
//    }

}
