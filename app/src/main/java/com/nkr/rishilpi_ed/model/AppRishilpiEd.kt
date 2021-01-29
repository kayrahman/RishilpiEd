package com.nkr.rishilpi_ed.model

import androidx.multidex.MultiDexApplication
import com.nkr.rishilpi_ed.data.ICloseupVideoLocalDatasource
import com.nkr.rishilpi_ed.data.local.CloseupVideoLocalRepo
import com.nkr.rishilpi_ed.data.local.LocalDB
import com.nkr.rishilpi_ed.ui.dashboard.DashboardViewModel
import com.nkr.rishilpi_ed.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppRishilpiEd : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel {
                HomeViewModel(get() as ICloseupVideoLocalDatasource)
            }

            single { CloseupVideoLocalRepo(get()) as ICloseupVideoLocalDatasource }
            single { LocalDB.createCloseUpVideoDao(this@AppRishilpiEd) }

        }


        startKoin {
            androidContext(this@AppRishilpiEd)
            modules(listOf(myModule))
        }

    }



}