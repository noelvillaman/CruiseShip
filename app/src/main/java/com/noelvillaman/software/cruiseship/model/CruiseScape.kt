package com.noelvillaman.software.cruiseship.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CruiseScape {
    @SerializedName("shipName")
    @Expose
    var shipName: String? = null

    @SerializedName("shipFacts")
    @Expose
    var shipFacts: ShipFacts? = null
}
