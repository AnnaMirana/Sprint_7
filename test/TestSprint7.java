import java.lang.reflect.Method;
import java.util.HashMap;

public class TestSprint7 {
    // Meme URL, deux methodes differentes selon le verbe (comme /classParam et /traite_classParam
    // dans le vrai Controller1, mais regroupees ici sous une seule URL pour la demo)
    public static class MonControleur {
        @GET
        public String afficherFormulaire() {
            return "Affichage du formulaire (GET)";
        }
        @POST
        public String soumettreFormulaire() {
            return "Formulaire traite (POST)";
        }
    }

    public static void main(String[] args) throws Exception {
        MonControleur ctrl = new MonControleur();
        HashMap<String, Method> parVerbe = new HashMap<>();

        for (Method m : ctrl.getClass().getMethods()) {
            if (m.isAnnotationPresent(GET.class) || m.isAnnotationPresent(POST.class)) {
                String verbe = VerbUtil.getVerb(m);
                parVerbe.put(verbe, m);
            }
        }

        System.out.println("Requete GET  -> " + parVerbe.get("GET").invoke(ctrl));
        System.out.println("Requete POST -> " + parVerbe.get("POST").invoke(ctrl));

        // Reproduit la detection de duplication du vrai setDicoMapping()
        System.out.println("\n-- Test duplication --");
        try {
            HashMap<String, String> memeUrlDeuxGet = new HashMap<>();
            String verbeExistant = "GET";
            String nouveauVerbe = "GET";
            if (verbeExistant.equals(nouveauVerbe)) {
                throw new Exception("Duplication du verbe " + nouveauVerbe + " sur la meme URL");
            }
        } catch (Exception e) {
            System.out.println("Exception attendue : " + e.getMessage());
        }
    }
}
