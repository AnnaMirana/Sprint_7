import java.lang.reflect.Method;

/**
 * Sprint 7 : distinguer les methodes selon le verbe HTTP associe (GET/POST).
 * Correspond a Outil.getVerb() dans le vrai framework.
 */
public class VerbUtil {
    public static String getVerb(Method meth) {
        String verb = "GET"; // GET par defaut si rien n'est precise
        if (meth.isAnnotationPresent(POST.class)) {
            verb = "POST";
        }
        if (meth.isAnnotationPresent(GET.class)) {
            verb = "GET";
        }
        return verb;
    }
}
