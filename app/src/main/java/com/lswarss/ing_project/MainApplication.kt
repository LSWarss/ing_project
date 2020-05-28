package com.lswarss.ing_project

import android.app.Application
import com.lswarss.ing_project.modules.CommentModule
import com.lswarss.ing_project.modules.PhotoModule
import com.lswarss.ing_project.modules.PostModule
import com.lswarss.ing_project.modules.UserModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    var listofModules =
        listOf(
            PostModule.mainModule,
            UserModule.mainModule,
            CommentModule.mainModule,
            PhotoModule.mainModule
        )


    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MainApplication)

            // load properties from assets/koin.properties file
            androidFileProperties()

            modules(listofModules)
        }

    }
}