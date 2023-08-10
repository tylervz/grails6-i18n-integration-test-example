package helloworld

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

@GrailsCompileStatic
class ExampleController {

    ExampleService exampleService
    MessageSource messageSource

    def index() {
        String message = exampleService.getMessage()
        render([message: message] as JSON)
    }

    def test() {
        String message = messageSource.getMessage("default.paginate.next",
            [].toArray(), LocaleContextHolder.locale)
        render([message: message] as JSON)
    }

    def useJsonViews() {
        String message = messageSource.getMessage("default.paginate.next",
                [].toArray(), LocaleContextHolder.locale)
        Map results = [message: message]
        respond results
    }
}
