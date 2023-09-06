package com.example.hanium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapView = MapView(context)
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(
            37.514813147383926, 126.9379807100462),
            1, true)
        val mapLayout = view.findViewById<FrameLayout>(R.id.map_view)

        mapView.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))
        mapView.addPOIItem(setMarkers())

        mapLayout.addView(mapView)
    }

    private fun setMarkers() : MapPOIItem {
        //마커 추가
        val marker = MapPOIItem()
        marker.apply {
            itemName = "수산시장명"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.514813147383926, 126.9379807100462)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        return marker
    }

    class CustomBalloonAdapter(inflater: LayoutInflater): CalloutBalloonAdapter {
        val mCalloutBallon: View = inflater.inflate(R.layout.balloon_layout, null)
        val name: TextView = mCalloutBallon.findViewById(R.id.market_name)
        val category: TextView = mCalloutBallon.findViewById(R.id.market_category)
        val rate: TextView = mCalloutBallon.findViewById(R.id.market_rate)
        val ratingBar: RatingBar = mCalloutBallon.findViewById(R.id.market_ratingBar)

        override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
            if (poiItem != null) {
                name.text = poiItem.itemName
                category.text = "회"
                rate.text = "4.0"
                ratingBar.rating = 4.0F
            }
            return mCalloutBallon
        }
        override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {

            return mCalloutBallon
        }
    }
}