class Reservation:
    def __init__(self, objet, duree):
        if not objet.est_disponible():
            raise Exception(f"L'objet '{objet.nom}' est déjà loué.")
        self.objet = objet
        self.duree = duree
        if not self.objet.reserver():
            raise Exception("Échec de la réservation.")

    def annuler(self):
        self.objet.liberer()
        print(f"Réservation annulée pour {self.objet.nom}")

    def afficher(self):
        print(f"{self.objet.get_type()} '{self.objet.nom}' réservé pour {self.duree} jour(s).")
