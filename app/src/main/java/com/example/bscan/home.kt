package com.example.bscan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.mainviewmodel
import com.demo.mainviewmodelfactory
import com.example.api.MyListAdapter
import com.example.api.bnbprice
import com.example.api.repositary
import com.example.api.stats.beptransferscan
import com.example.api.tokens
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.customalesrtdialog.*
import kotlinx.android.synthetic.main.customalesrtdialog.view.*

class home : AppCompatActivity() {
    private  lateinit var viewModel: mainviewmodel
    val l : MutableList<String> = ArrayList()
    val l9 : MutableList<String> = ArrayList()
    val l2 : MutableList<String> = ArrayList()
    val l3 : MutableList<String> = ArrayList()
    val l4 : MutableList<String> = ArrayList()
    val l5: MutableList<String> = ArrayList()
    val l6: MutableList<String> = ArrayList()
    val l7 : MutableList<String> = ArrayList()
    val l8 : MutableList<String> = ArrayList()


    lateinit var list : tokens
    lateinit var list1 : beptransferscan
    lateinit var list2 : bnbprice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater
        inflater=getMenuInflater()
        inflater.inflate(R.menu.addproductmenu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(item.itemId==R.id.addpro){
          //Inflate the dialog with custom view
          val mDialogView = LayoutInflater.from(this).inflate(R.layout.customalesrtdialog, null)
          //AlertDialogBuilder
          val mBuilder = AlertDialog.Builder(this)
                  .setView(mDialogView)
                  .setTitle("Add wallet")
          //show dialog
          val  mAlertDialog = mBuilder.show()
          mDialogView.dialogLoginBtn.setOnClickListener {
              mAlertDialog.dismiss()
              //get text from EditTexts of custom layout
              val adress = mDialogView.dialogNameEt.text.toString()
              val contractaddress = mDialogView.dialogEmailEt.text.toString()
              if (adress != null && contractaddress != null) {
                  val repository = repositary()
                  val viewModelFactory = mainviewmodelfactory(repository)
                  viewModel =
                      ViewModelProvider(this, viewModelFactory).get(mainviewmodel::class.java)

                  viewModel.gettokens(
                      contractaddress,
                      adress,
                      1,
                      5,
                      0,
                      999999999,
                      "asc",
                      "RSV8C5U2MT5KX6XYGBMBVUEIP1T9DFRCXJ"
                  )
                  viewModel.myresponce.observe(this, { responce ->
                      if (responce.isSuccessful) {

                          list1 = responce.body()!!
                          l7.add("${list1.result[0]}${list1.result[0]}${list1.result[0]}")
                          viewModel.getrealtokens(
                              adress,
                              1,
                              100,
                              "RSV8C5U2MT5KX6XYGBMBVUEIP1T9DFRCXJ\n"
                          )
                          viewModel.myresponce3.observe(this, {
                              if (it.isSuccessful) {
                                  list = it.body()!!
                                  l.add("${list.result[0].TokenName}")
                                  l2.add(
                                      "${
                                          list.result[0].TokenQuantity.substring(
                                              0,
                                              list.result[0].TokenDivisor.toInt()
                                          )
                                      } price:15.7"
                                  )
                                  l3.add("${list.result[1].TokenName}")
                                  l4.add(
                                      "${
                                          list.result[1].TokenQuantity.substring(
                                              0,
                                              list.result[1].TokenDivisor.toInt()
                                          )
                                      } price:0.00"
                                  )
                                  l5.add("${list.result[2].TokenName}")
                                  l6.add(
                                      "${
                                          list.result[2].TokenQuantity.substring(
                                              0,
                                              list.result[2].TokenDivisor.toInt()
                                          )
                                      } price : 0.00"
                                  )
                                  viewModel.getpost2()
                                  viewModel.myresponce2.observe(this, {
                                      if (it.isSuccessful) {
                                          list2 = it.body()!!
                                          l8.add("${list2.result.ethbtc}")
                                          l9.add("${list2.result.ethusd}")
                                          val myListAdapter = MyListAdapter(
                                              this,
                                              l8.toTypedArray(),
                                              l9.toTypedArray(),
                                              l.toTypedArray(),
                                              l3.toTypedArray(),
                                              l5.toTypedArray(),
                                              l2.toTypedArray(),
                                              l4.toTypedArray(),
                                              l6.toTypedArray(),
                                              l7.toTypedArray()
                                          )
                                          fofo.visibility = View.GONE
                                          myListAdapter.notifyDataSetChanged()

                                          oeo.adapter = myListAdapter


                                      }
                                  })
                              }
                          })

                          Log.d("look", "${responce.body()}  ${responce.code()}")

                      } else {
                          Log.d("err", "ERROR cause $responce ${responce.code()} ")
                      }

                  })
              }
              //cancel button click of custom layout
              mDialogView.dialogCancelBtn.setOnClickListener {
                  //dismiss dialog
                  mAlertDialog.dismiss()
              }
              Toast.makeText(this, "addproo", Toast.LENGTH_LONG).show()
          }
      } else if (item.itemId==R.id.wallets){
          Toast.makeText(this,"wallets",Toast.LENGTH_LONG).show()


      }
        return super.onOptionsItemSelected(item)
    }
}