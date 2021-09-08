package com.noelvillaman.software.cruiseship.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CruiseBliss {
    @SerializedName("shipName")
    @Expose
    var shipName: String? = null

    @SerializedName("shipFacts")
    @Expose
    var shipFacts: ShipFacts? = null
    override fun toString(): String {
        return "CruiseBliss(shipName=$shipName, shipFacts=$shipFacts)"
    }


}

class ShipFacts {
    @SerializedName("passengerCapacity")
    @Expose
    var passengerCapacity: String? = null

    @SerializedName("inauguralDate")
    @Expose
    var inauguralDate: String? = null

    @SerializedName("crew")
    @Expose
    var crew: String? = null
    override fun toString(): String {
        return "ShipFacts(passengerCapacity=$passengerCapacity, inauguralDate=$inauguralDate, crew=$crew)"
    }


}
