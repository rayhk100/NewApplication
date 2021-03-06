package com.example.myapplication.ui.routes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRoutesBinding
import com.example.myapplication.model.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val routes = listOf("1","1A","2","2A","2B","2D","2E","2F","2X","3B","3C","3D","3M","3X","5","5A","5C","5D","5M","5P","5R","5X","6","6C","6D","6F","6P","6X","7","7B","7M","8","8A","8P","9","10","11","11B","11C","11D","11K","11X","12","12A","13D","13M","13P","13X","14","14B","14D","14H","14X","15","15A","15X","16","16M","16X","17","18","21","23","23M","24","26","26M","26X","27","28","28B","28S","29M","30","30X","31","31B","31M","31P","32","32H","32M","33","33A","34","34M","35A","35X","36","36A","36B","36M","36X","37","37M","38","38A","38B","38P","39A","39M","40","40A","40P","40S","40X","41","41A","41M","41P","42","42A","42C","42M","43","43A","43B","43C","43D","43M","43P","43X","44","44M","45","46","46P","46X","47A","47X","48P","48X","49P","49X","51","52X","53","54","57M","58M","58P","58X","59A","59M","59X","60M","60X","61A","61M","61X","62X","63X","64K","64P","64S","65K","66M","66X","67M","67X","68","68A","68E","68F","68M","68X","69C","69M","69P","69X","70K","71A","71B","71K","71S","72","72A","72C","72X","73","73A","73B","73K","73X","74A","74B","74C","74D","74E","74K","74P","74X","75K","75P","75X","76K","77K","78K","79K","80","80A","80K","80M","80P","80X","81","81C","81K","81S","82B","82C","82K","82P","82X","83A","83K","83S","83X","84M","85","85A","85B","85K","85M","85S","85X","86","86A","86C","86K","86P","86S","87B","87D","87E","87K","87P","87S","88","88K","88X","89","89B","89C","89D","89P","89S","89X","91","91M","91P","91S","92","93A","93K","93M","94","95","95M","96R","98A","98C","98D","98P","98S","99","99R","108","203C","203E","203S","203X","205M","208","211","211A","213A","213B","213D","213M","213S","213X","214","215P","215X","216M","219X","224X","230X","234A","234B","234C","234D","234P","234X","235","235M","237A","238M","238P","238X","240X","241X","242X","243M","243P","248M","249M","249X","251A","251B","251M","252","252B","252X","258A","258D","258P","258S","258X","259B","259C","259D","259E","259X","260B","260C","260X","261","261B","261P","261S","261X","263","263A","263C","264R","265B","265M","265S","267X","268A","268B","268C","268M","268P","268X","269A","269B","269C","269D","269M","269P","269S","270","270A","270B","270C","270D","270P","270S","271","271B","271P","271S","271X","272A","272E","272K","272P","272S","272X","273","273A","273B","273C","273D","273P","273S","274","274P","274X","275R","276","276A","276B","276P","277A","277E","277P","277X","278A","278K","278P","278X","279A","279X","280X","281A","281B","281M","281X","282","283","284","286C","286M","286P","286X","287X","288","288A","288B","288C","289K","289R","290","290A","290B","290X","291P","292P","293S","296A","296C","296D","296M","296P","297","297P","298E","299X","373","603","603A","603P","603S","613","673","673P","934","934A","935","936","936A","960","960A","960B","960C","960P","960S","960X","961","961P","968","968A","968X","978","978A","978B","B1","W2","W3","K12","K14","K17","K18","X42C","X42P","T74","X89D","T270","T277","N3D","N36","N39","N41X","N73","N216","N237"
    ,"N241","N252","N260","N269","N271","N281","N283","N287","N290","N293","N368","N373")
class RoutesFragment : Fragment() {
    private lateinit var binding: FragmentRoutesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        binding = FragmentRoutesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RoutesFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.routesRecyclerView.apply {
            adapter = RoutesListAdapter()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

//        MainScope().launch {
//            fetchRoutes()
//        }
        (binding.routesRecyclerView.adapter as RoutesListAdapter).submitList(Route.fetchAll())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        Log.d("RoutesFragment","onCreateOptionsMenu menu size = ${menu.size()}")

//        menu.findItem(R.id.search_view).actionView
        (menu.findItem(R.id.search_view).actionView as? SearchView)?.let {
            Log.d("RoutesFragment","menu.findItem(R.id.search_view)")
            it.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("RoutesFragment","onQueryTextSubmit")
                    query?.let{filterByText(it)}
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("RoutesFragment","onQueryTextChange")
                    newText?.let{filterByText(it)}
                    return true
                }
            })
        }
    }

    fun filterByText(text:String) {
        (binding.routesRecyclerView.adapter as RoutesListAdapter).submitList(Route.fetchAll().filter {
            it.title.startsWith(text)
        })
    }


    suspend fun fetchRoutes() = withContext(Dispatchers.IO) {
        var routes = Route.fetchAll()

        MainScope().launch {
            (binding.routesRecyclerView.adapter as RoutesListAdapter).submitList(routes)
        }
    }
}