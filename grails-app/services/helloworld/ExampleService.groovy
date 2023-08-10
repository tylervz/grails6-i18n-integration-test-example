package helloworld

import grails.compiler.GrailsCompileStatic
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

@GrailsCompileStatic
class ExampleService {

    MessageSource messageSource

    String getMessage() {
        String message = messageSource.getMessage("default.paginate.prev",
            [].toArray(), LocaleContextHolder.locale)
        return message
    }
}
