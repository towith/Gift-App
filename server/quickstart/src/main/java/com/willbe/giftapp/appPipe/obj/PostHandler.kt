package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.Application
import com.willbe.giftapp.Constant
import com.willbe.giftapp.domain.App_
import com.willbe.giftapp.domain.Artifact
import com.willbe.giftapp.domain.Config_
import com.willbe.giftapp.domain.WidgetConfig
import com.willbe.giftapp.dto.App_DTOService
import com.willbe.giftapp.repository.ArtifactRepository
import com.willbe.giftapp.repository.Config_Repository
import com.willbe.giftapp.repository.WidgetConfigRepository
import com.willbe.giftapp.security.UserContext
import java.io.File

class PostHandler : Handler {

    override fun doHandle(context: Context) {
        var app_DTOService: App_DTOService = Application.applicationContext!!.getBean("app_DTOService") as App_DTOService
        var appEntity = app_DTOService.toEntity(context.app)

        var saveConfig = saveConfig(context, appEntity)
        saveArtifact(context, appEntity, saveConfig)
    }

    private fun saveConfig(context: Context, appEntity: App_): Config_? {
        var config_Repository = Application.applicationContext!!.getBean("config_Repository") as Config_Repository
        var config_ = Config_()
        config_.app = appEntity
        config_.user = UserContext.getCurrentUser()
        var config = config_Repository.save(config_)


        var widgetConfigRepository = Application.applicationContext!!.getBean("widgetConfigRepository") as WidgetConfigRepository
        var appWidgetDTOList = context.appWidgetDTOList
        for (appWidgetDTO in appWidgetDTOList) {
            var widgetConfig = WidgetConfig()
            widgetConfig.config = config_
            widgetConfig.inputvalue = appWidgetDTO.inputValue
            widgetConfigRepository.save(widgetConfig)
        }
        return config
    }

    private fun saveArtifact(context: Context, appEntity: App_, saveConfig: Config_?) {
        var artifactRepo: ArtifactRepository = Application.applicationContext!!.getBean("artifactRepository") as ArtifactRepository

        var artifact = Artifact()
        artifact.app = appEntity
        var currentUser = UserContext.getCurrentUser()
        artifact.user = currentUser
        artifact.outputPath = context.outputPath + File.separator + Constant.apkPath;
        artifact.config = saveConfig
        artifactRepo.save(artifact)
    }
}