from abc import ABC, abstractmethod

class Objet(ABC):
    def __init__(self, nom: str):
        self.nom = nom
        self.est_reserve = False

    def reserver(self) -> bool:
        if self.est_reserve:
            return False
        self.est_reserve = True
        return True

    def liberer(self):
        self.est_reserve = False

    def est_disponible(self) -> bool:
        return not self.est_reserve

    @abstractmethod
    def get_type(self) -> str:
        pass
