package com.noelvillaman.software.cruiseship.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CruiseSky {
    @SerializedName("shipName")
    @Expose
    var shipName: String? = null

    @SerializedName("shipFacts")
    @Expose
    var shipFacts: ShipFacts? = null

    override fun toString(): String {
        return "CruiseSky(shipName=$shipName, shipFacts=$shipFacts)"
    }


}