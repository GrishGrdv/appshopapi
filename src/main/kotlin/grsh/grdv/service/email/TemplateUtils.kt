package grsh.grdv.service.email

import groovy.text.SimpleTemplateEngine
import kotlin.io.path.Path
import kotlin.io.path.readText


object TemplateUtils {

    fun buildContentByTemplate(pathToTemplate: String, binding: Map<String, Any>): String? {
        val textTemplate = Path(pathToTemplate).readText()
        if (textTemplate.isBlank()) {
            return null
        }

        val template = SimpleTemplateEngine().createTemplate(textTemplate).make(binding)
        return template.toString()
    }
}