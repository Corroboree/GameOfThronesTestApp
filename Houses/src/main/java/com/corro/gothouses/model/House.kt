package com.corro.gothouses.model

import com.fasterxml.jackson.annotation.JsonProperty

data class House(
    @JsonProperty("ancestralWeapons")
    val ancestralWeapons: List<String>,
    @JsonProperty("cadetBranches")
    val cadetBranches: List<String>,
    @JsonProperty("coatOfArms")
    val coatOfArms: String,
    @JsonProperty("currentLord")
    val currentLord: String,
    @JsonProperty("diedOut")
    val diedOut: String,
    @JsonProperty("founded")
    val founded: String,
    @JsonProperty("founder")
    val founder: String,
    @JsonProperty("heir")
    val heir: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("overlord")
    val overlord: String,
    @JsonProperty("region")
    val region: String,
    @JsonProperty("seats")
    val seats: List<String>,
    @JsonProperty("swornMembers")
    val swornMembers: List<String>,
    @JsonProperty("titles")
    val titles: List<String>,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("words")
    val words: String
)