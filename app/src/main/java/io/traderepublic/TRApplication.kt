package io.traderepublic

import android.app.Application
import io.traderepublic.data.di.DataModule
import io.traderepublic.domain.di.DomainModule
import io.traderepublic.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class TRApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@TRApplication)
    }

    loadModules()
  }

  private fun loadModules() {
    DomainModule.load()
    DataModule.load()
    PresentationModule.load()
  }
}
