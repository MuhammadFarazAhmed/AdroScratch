package com.example.adro

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.adro.prefs.PreferencesHelper
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import java.util.Locale
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


object LocaleManager : KoinComponent {

    private val preferencesHelper: PreferencesHelper = get()

    fun setLocale(context: Context): Context {
        val selectedLanguage = getSelectedLanguage()
       return updateResources(context, selectedLanguage)
    }

    fun setNewLocale(context: Context, language: String): Context {
        persistLanguage(language)
        return updateResources(context, language)
    }

    private fun getSelectedLanguage(): String =
        runBlocking { preferencesHelper.getFirstPreference(stringPreferencesKey("lang"), "en-US") }


    private fun persistLanguage(language: String) {
        runBlocking { preferencesHelper.putPreference(stringPreferencesKey("lang"), language) }
    }

    fun getDefaultLanguage(): String {
        return Locale.getDefault().language
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }
}