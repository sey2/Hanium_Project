package com.example.hanium

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {

    private val markers = mutableListOf<MapPOIItem>()

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
        mapView.setPOIItemEventListener(MarkerEventListener(requireContext()))
        addMarkers()
        mapView.addPOIItems(markers.toTypedArray())

        mapLayout.addView(mapView)
    }

    private fun addMarkers() {
        markers.add(setMarkers("손선장", 37.514866602203625, 126.93683823427783, 4))
        markers.add(setMarkers("용궁횟집", 37.51464580796735, 126.93674793099558, 2))
        markers.add(setMarkers("호남꽃게", 37.5146976716416, 126.93685251561871, 1))
        markers.add(setMarkers("녹동수산", 37.514587197291014, 126.93666314675272, 1))
        markers.add(setMarkers("로또수산", 37.51444534380622, 126.9367650668892, 2))
        markers.add(setMarkers("꽁지횟집", 37.51441841498292, 126.9369545511316, 2))
        markers.add(setMarkers("88수산", 37.51455814889883, 126.93710147862417, 1))
        markers.add(setMarkers("일성수산", 37.514456891262185, 126.93729950857944, 1))
        markers.add(setMarkers("성난물고기", 37.514729384678496, 126.9371861687839, 1))
        markers.add(setMarkers("김씨마구로", 37.51483081215361, 126.9373076791525, 5))
        markers.add(setMarkers("청해에프엔에스", 37.51467991060177, 126.93733891111319, 1))
        markers.add(setMarkers("대신수산",  37.51475885174288, 126.93753396308824, 4))
        markers.add(setMarkers("진주식당", 37.51463051405285, 126.93763869835601, 2))
        markers.add(setMarkers("청명수산", 37.514540436103665, 126.93768119013575, 1))
        markers.add(setMarkers("동해바다", 37.51500229268388, 126.93785613006165,2 ))
        markers.add(setMarkers("노량진제주전복", 37.5149346802205, 126.93778549104015, 1))
        markers.add(setMarkers("대원수산", 37.51486490604034, 126.93788734978935, 2))
        markers.add(setMarkers("주문진", 37.51475226872571, 126.93786482076732, 1))
        markers.add(setMarkers("금성수산", 37.51500466251518, 126.93807952477391, 1))
        markers.add(setMarkers("형제상회", 37.51487177193762, 126.93809377357417, 1))
        markers.add(setMarkers("성전물산", 37.51476819802601, 126.938173037447, 1))
        markers.add(setMarkers("태성수산", 37.51463078465194, 126.93815335626284, 1))
        markers.add(setMarkers("하늘채별관", 37.514961955160075, 126.93825205612954, 2))
        markers.add(setMarkers("하늘채본관", 37.51499136759572, 126.93850087878447, 2))
        markers.add(setMarkers("청해진",  37.51484716433778, 126.93841899091228, 2))
        markers.add(setMarkers("전주상회", 37.51475484538612, 126.93848410609063, 2))
        markers.add(setMarkers("우정수산", 37.51465570260051, 126.93842197602379, 2))
        markers.add(setMarkers("서울수산", 37.51500501781421, 126.93876102574123, 1))
        markers.add(setMarkers("금학횟집", 37.514896834310214, 126.93863951865417, 2))
        markers.add(setMarkers("수산회관", 37.514757215496545, 126.93871032788236, 2))
        markers.add(setMarkers("함평천지", 37.51507508655825, 126.93922755692452, 1))
        markers.add(setMarkers("노량진수산수협", 37.51505251051089, 126.93912860195603, 1))
        markers.add(setMarkers("전북수산", 37.51485877292799, 126.93908634230317, 1))
        markers.add(setMarkers("전라상회", 37.515005328560335, 126.93936334818282, 1))
        markers.add(setMarkers("성진수산", 37.51505949310614, 126.9395669065448, 1))
        markers.add(setMarkers("부안꽃게", 37.51490181752295, 126.93956703368833, 4))
        markers.add(setMarkers("전라도함평", 37.5148229160047, 126.9394426741461, 2))
        markers.add(setMarkers("당진수산", 37.514716924633596, 126.93920239719621, 1))
        markers.add(setMarkers("하루", 37.514851026141564, 126.93719737803424, 6))
        markers.add(setMarkers("수산약국", 37.51478823439645, 126.93772340105568, 7))
        markers.add(setMarkers("Sh수협은행", 37.5145895361198, 126.93682432882682, 8))
    }

    private fun setMarkers(name: String, latitude: Double, longitude: Double, category: Int) : MapPOIItem {
        //마커 추가
        val marker = MapPOIItem()
        marker.apply {
            itemName = name
            mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            tag = category
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        return marker
    }

    class CustomBalloonAdapter(inflater: LayoutInflater): CalloutBalloonAdapter {
        val mCalloutBallon: View = inflater.inflate(R.layout.balloon_layout, null)
        val name: TextView = mCalloutBallon.findViewById(R.id.market_name)
        val category: TextView = mCalloutBallon.findViewById(R.id.market_category)

        override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
            if (poiItem != null) {
                name.text = poiItem.itemName
                category.text = when(poiItem.tag){
                    1 -> "수산물 판매"
                    2 -> "회"
                    4 -> "게/대게"
                    5 -> "참치회"
                    6 -> "카페"
                    7 -> "약국"
                    8 -> "은행"
                    else -> ""
                }
            }
            return mCalloutBallon
        }
        override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {

            return mCalloutBallon
        }
    }

    class MarkerEventListener(private val context: Context): MapView.POIItemEventListener{
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        }
        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            mapView: MapView?,
            poiItem: MapPOIItem?,
            buttonType: MapPOIItem.CalloutBalloonButtonType?
        ) {
            Log.d("TAG", "checkingLog: down fun")
            val builder = AlertDialog.Builder(context)
            val selectList = arrayOf("상세 정보", "북마크 추가", "닫기")
            builder.setTitle("${poiItem?.itemName}")
            builder.setItems(selectList) {dialog, which ->
                when(which) {
                    0 -> TODO("상세 정보 창")
                    1 -> {Toast.makeText(context, "[${poiItem?.itemName}] 북마크 추가되었습니다.", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()}
                    2 -> dialog.dismiss()
                }
            }
            builder.show()
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
        }

    }
}