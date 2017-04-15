package com.pr0gramm.app

import android.content.SharedPreferences
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.pr0gramm.app.api.categories.ExtraCategories
import com.pr0gramm.app.api.pr0gramm.Api
import com.pr0gramm.app.api.pr0gramm.LoginCookieHandler
import com.pr0gramm.app.io.Cache
import com.pr0gramm.app.services.*
import com.pr0gramm.app.services.config.ConfigService
import com.pr0gramm.app.services.gif.GifToVideoService
import com.pr0gramm.app.services.gif.MyGifToVideoService
import com.pr0gramm.app.services.proxy.ProxyService
import com.pr0gramm.app.ui.AdService
import com.pr0gramm.app.ui.FancyExifThumbnailGenerator
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import javax.inject.Inject

internal class KApp(private val app: ApplicationClass) : KodeinAware {

    @Inject lateinit var inMemoryCacheService: InMemoryCacheService
    @Inject lateinit var fancyExifThumbnailGenerator: FancyExifThumbnailGenerator
    @Inject lateinit var proxyService: ProxyService
    @Inject lateinit var downloader: Downloader
    @Inject lateinit var UserSuggestionService: UserSuggestionService
    @Inject lateinit var Api: Api
    @Inject lateinit var OkHttpClient: OkHttpClient
    @Inject lateinit var Cache: Cache
    @Inject lateinit var UserService: UserService
    @Inject lateinit var UploadService: UploadService
    @Inject lateinit var ExtraCategories: ExtraCategories
    @Inject lateinit var ConfigService: ConfigService
    @Inject lateinit var BookmarkService: BookmarkService
    @Inject lateinit var LoginCookieHandler: LoginCookieHandler
    @Inject lateinit var InboxService: InboxService

    init {
        app.appComponent.get().inject(this)
    }

    override val kodein: Kodein by Kodein.lazy {
        import(autoAndroidModule(app))

        bind<SharedPreferences>(overrides = true) with instance(app.appComponent.get().sharedPreferences())

        bind<Settings>() with instance(Settings.get())
        bind<OkHttpClient>() with instance(OkHttpClient)
        bind<Api>() with instance(Api)

        bind<Picasso>() with instance(app.appComponent.get().picasso())
        bind<InMemoryCacheService>() with instance(inMemoryCacheService)
        bind<FancyExifThumbnailGenerator>() with instance(fancyExifThumbnailGenerator)

        bind<Cache>() with instance(Cache)
        bind<ProxyService>() with instance(proxyService)

        bind<Downloader>() with instance(downloader)

        bind<ExtraCategories>() with instance(ExtraCategories)
        bind<ConfigService>() with instance(ConfigService)
        bind<BookmarkService>() with instance(BookmarkService)
        bind<LoginCookieHandler>() with instance(LoginCookieHandler)
        bind<InboxService>() with instance(InboxService)

        bind<UserService>() with instance(UserService)
        bind<UserSuggestionService>() with instance(UserSuggestionService)
        bind<UploadService>() with instance(UploadService)


        bind<AdminService>() with singleton { AdminService(instance()) }
        bind<AdService>() with singleton { AdService(instance(), instance()) }
        bind<ContactService>() with singleton { ContactService(instance()) }
        bind<FeedbackService>() with singleton { FeedbackService(instance()) }
        bind<GifDrawableLoader>() with singleton { GifDrawableLoader(app, instance()) }
        bind<GifToVideoService>() with singleton { MyGifToVideoService(instance()) }
        bind<InviteService>() with singleton { InviteService(instance()) }

        bind<NotificationService>() with singleton { NotificationService(instance(), instance(), instance(), instance()) }

        bind<RulesService>() with singleton { RulesService(instance()) }

        bind<NavigationProvider>() with scopedSingleton(androidActivityScope) {
            NavigationProvider(instance(), instance(), instance(), instance(), instance(), instance())
        }
    }
}
