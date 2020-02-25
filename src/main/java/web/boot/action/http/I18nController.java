package web.boot.action.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

/**
 * not work
 * error There was an unexpected error (type=Internal Server Error, status=500).
 * No primary or default constructor found for interface org.springframework.context.MessageSource
 * @author z
 */
@Controller
public class I18nController {
    @GetMapping("/i18n")
    public String i18n(Model model, @Autowired MessageSource messageSource) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("message", messageSource.getMessage("message", null, locale));
        return "i18n";
    }
}
