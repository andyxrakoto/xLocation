from voiture import Voiture
from maison import Maison
from reservation import Reservation


def afficher_menu():
    print("\n=== Système de location ===")
    print("a. Ajouter un bien (Véhicule ou Habitation)")
    print("b. Effectuer une location")
    print("c. Voir l'inventaire")
    print("d. Sortir")


def saisir_choix(prompt):
    return input(f"{prompt} ").strip()


def ajouter_objet(catalogue):
    type_bien = saisir_choix("Quel type ? (vehicule/habitation) :").lower()
    nom_bien = saisir_choix("Nom du bien :")

    if type_bien == "vehicule":
        catalogue.append(Voiture(nom_bien))
        print(f"[✔] Véhicule '{nom_bien}' enregistré.")
    elif type_bien == "habitation":
        catalogue.append(Maison(nom_bien))
        print(f"[✔] Habitation '{nom_bien}' enregistrée.")
    else:
        print("[✘] Type inconnu.")


def effectuer_reservation(catalogue):
    if not catalogue:
        print("[!] Aucun bien à réserver.")
        return

    print("\n--- Biens disponibles ---")
    for idx, objet in enumerate(catalogue):
        etat = "Libre" if objet.est_disponible() else "Occupé"
        print(f"[{idx}] {objet.get_type()} '{objet.nom}' ({etat})")

    try:
        indice = int(saisir_choix("Numéro du bien à louer :"))
        jours = int(saisir_choix("Nombre de jours :"))
        reservation = Reservation(catalogue[indice], jours)
        reservation.afficher()
    except (ValueError, IndexError):
        print("[✘] Entrée invalide.")
    except Exception as erreur:
        print(f"[✘] Erreur : {erreur}")


def lister_catalogue(catalogue):
    if not catalogue:
        print("[!] Aucun bien enregistré.")
        return

    print("\n--- Liste des biens ---")
    for idx, obj in enumerate(catalogue):
        statut = "Libre" if obj.est_disponible() else "Occupé"
        print(f"[{idx}] {obj.get_type()} - {obj.nom} ({statut})")


def boucle_principale():
    objets = []
    print("Bienvenue dans le gestionnaire de locations multitypes.")

    while True:
        afficher_menu()
        commande = saisir_choix("Votre choix :").lower()

        if commande == "a":
            ajouter_objet(objets)
        elif commande == "b":
            effectuer_reservation(objets)
        elif commande == "c":
            lister_catalogue(objets)
        elif commande == "d":
            print("Fermeture de session. Merci.")
            break
        else:
            print("[✘] Option inconnue. Veuillez réessayer.")


if __name__ == "__main__":
    boucle_principale()
