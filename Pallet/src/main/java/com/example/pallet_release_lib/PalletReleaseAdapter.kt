package com.example.pallet_release_lib

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pallet_release_lib.model.DataGetMaterialForEmptyPalletModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PalletReleaseAdapter(
        private val context: Context,
        private var dataList: ArrayList<DataGetMaterialForEmptyPalletModel>
) :
        RecyclerView.Adapter<PalletReleaseAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

                val date: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardDate)
                val uid: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardUID)
                val grn: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardGRN)
                val invoice: TextView =
                        itemView.findViewById(R.id.txtDetailedQualityInspectionInwardInvoice)
                val itemCode: TextView =
                        itemView.findViewById(R.id.txtDetailedQualityInspectionInwardMaterialCode)
                val itemQty: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardQty)
                val vn_vc: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardVC_VN)
                val batch: TextView = itemView.findViewById(R.id.txtDetailedQualityInspectionInwardBatch)
                val status: TextView =
                        itemView.findViewById(R.id.DetailedQualityInspectionInwardUnrestricted)
                val descipt: TextView =
                        itemView.findViewById(R.id.txtDetailedQualityInspectionInwardRestrictedDescription)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.adapter_empty_pallet, parent, false)
                return ViewHolder(view)
        }

        override fun getItemCount(): Int {
                return dataList.size
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

                val dateTime = LocalDateTime.parse(
                        "${dataList[position].date}",
                        DateTimeFormatter.ISO_DATE_TIME
                )

                holder.date.text = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                holder.uid.text = dataList[position].sku
                holder.grn.text = dataList[position].grnNumber
                holder.invoice.text = "NA"
                holder.itemCode.text = dataList[position].itemCode

                var vc = ""
                var vn = ""

                vc = if (dataList[position].vendorCode.isEmpty()) {

                        "NA"

                } else {

                        dataList[position].vendorCode

                }

                vn = if (dataList[position].vendorName.isEmpty()) {
                        "NA"
                } else {
                        dataList[position].vendorName
                }

                holder.vn_vc.text = "$vc / $vn"

                holder.itemQty.text = "${dataList[position].qty} ${dataList[position].uom}"
//            "${dataList[position].totalPacks} X ${if (dataList[position].packSize % 1 == 0.0) dataList[position].packSize.toInt() else dataList[position].packSize} / ${if (dataList[position].totalQty % 1 == 0.0) dataList[position].totalQty.toInt() else dataList[position].totalQty}  - ${if (dataList[position].actualQty % 1 == 0.0) dataList[position].actualQty.toInt() else dataList[position].actualQty} ${dataList[position].uom}"

                holder.descipt.text = dataList[position].itemDescription

                if (dataList[position].batchNumber.isNullOrEmpty()) {
                        holder.batch.text = "NA"
                } else {
                        holder.batch.text = dataList[position].batchNumber
                }

//        when (dataList[position].stockType) {
//            "X", "2" -> {
//                //            yellow
//                holder.status.setTextColor(Color.parseColor("#EDC01E"))
//                holder.status.text = "Quality Inspection"
//
//            }
//
//            "F" -> {
//                //          green
//                holder.status.setTextColor(Color.parseColor("#3CD62E"))
//                holder.status.text = "Unrestricted-use"
//                //            red
//            }
//
//            "S", "3" -> {
//                holder.status.setTextColor(Color.parseColor("#F54209"))
//                holder.status.text = "Blocked"
//            }
//
//            else -> {
//                holder.status.text = "NA"
//            }
//        }

        }
}