# CODEX 
  ![Immagine del gioco](https://github.com/lizz34/Codex_Naturalis/blob/main/images/Codex.jpg)

Riunite la pagine del Codex Naturalis, il manoscritto segreto che enumera le specie dei quattro regni che dimorano nelle foreste vergini. Giocate e piazzate le vostre carte in modo da sfruttare le risorse, completare gli obiettivi e redigere il manoscritto più elaborato!

Questo videogioco é un progetto svolto presso l'università di Bergamo e rappresenta la digitalizzazione del gioco da tavolo Codex Naturalis di Cranio Creations.

## indice
- [Istruzioni per giocare](#Istruzione)
- [Funzionalità principali del gioco](#Funzionalità)
- [Stato del progetto](#Stato)
- [Autori del progetto](#Autori)
- [Licenza](#Licenza)

## Istruzione

Per leggere il regolamento completo del gioco da tavolo: [Come giocare](https://www.craniocreations.it/storage/media/product_downloads/126/1516/CODEX_ITA_Rules_compressed.pdf) 

Per poter giocare a questa versione di Codex Naturalis, clonare la repository in locale e aprirla su Eclipse; in seguito eseguire il file Main.java contenuto nel package Main.
La prima cosa che viene richiesta è di inserire il numero e il nickname dei giocatori che desiderano partecipare alla partita (minimo 2 massimo 4).
Il sistema creerà in automatico i mazzi di tutte le carte e, in maniera randomica, verrà assegnato ad ogni giocatore: una carta obiettivo, 3 carte nella mano (2 risorsa e 1 oro) e una carta starter (già inserita nella matrice di gioco in posizione 24x24).
Ai giocatori viene permesso di scegliere se giocare la propria carta starter di fronte o di retro.

Per ogni turno il giocatore visualizza un menù con le seguenti opzioni: 
- posiziona una nuova carta (comprende il posizionamento della carta e il pescaggio di un'altra carta da quelle disponibili sul banco)
- guarda le carte che puoi pescare (quelle disponibili sul banco)
- visualizza la tua area di gioco
- visualizza le carte che hai in mano
- rovescia una carta della mano (per poter giocare le carte a rovescio)
- visualizza il tuo punteggio
- visualizza gli obiettivi
  
Il turno di un giocatore termina solo dopo che è stata posizionata correttamente una carta (opzione 1 del menù). E' consigliabile fare una panoramica su tutte le altre opzioni prima di scegliere quella relativa al posizionamento della carta. 
Per posizionare una carta tramite la [funzione per posizionare la carta](#posizionaCarta) è necessario inserire:
- il numero della carta del proprio mazzo che si vuole inserire (da 1 a 3)
- il numero della riga, della colonna e dell'angolo che corrispondono alla carta già presente nella matrice, sulla quale si vuole posizionare la nuova carta. Il campo da gioco viene rappresentato tramite una matrice (per visualizzare, opzione 3 del menù): a schermo verrà visualizzata una lista di carte (indicizzate tramite la loro riga e colonna: valori da 0 a 49) che mostrano una lista dei loro 4 angoli visibili. Successivamente verrà chiesto l'angolo sul quale si vorrà operare (da 1 a 4 se la carta é giocata di fronte, da 5 a 8 se di retro) scegliendo a partire dall'anglo in alto a sinistra per poi continuare in senso orario.
  Posionata la carta possono continuare i giocatori successivi e in automatico il programma salverà i punti fatti (visualizzabili scegliendo l'opzione 6 nel menu' nel prorprio turno).

  ![Disposizione degli angoli](https://github.com/lizz34/progettoUni/blob/main/images/fronte%20e%20retro%20carte.png)
 
Nel caso si voglia giocare una carta di retro, bisogna selezionare l'opzione 5 del menù prima di procedere con il posizionamento della carta; in questo caso, inserire il numero della carta della propria mano che si vuole girare.

Il termine della partita è decretato da: uno dei mazzi di carte finisce; un giocatore raggiunge (o supera) i 20 punti e tutti i giocatori hanno giocato lo stesso numero di turni. Qui il giocatore con più punti non è detto che vinca in quanto bisogna ancora contare tutti i punti extra delle carte obiettivo (2 obiettivi comuni e uno segreto), che verranno calcolati e sommati al proprio punteggio.
il giocatore con più punti sarà il vincitore.

In caso di parità il giocatore che ha realizzato più carte obiettivo vincerà, se saranno uguali anche queste verrà condivisa la vittoria.


## Funzionalità
- Funzione per posizionare una carta sul campo da gioco:
Posiziona una carta dalla mano del giocatore nel suo campo da gioco, se l'azione va a buon fine la carta viene anche rimossa dalla sua mano. Per il conteggio dei punti ottenuti in seguito al posizionamento della carta si aprono due possibili scenari: se la carta è giocata sul retro non viene effettuato nessun controllo dal momento che non da punteggio; se la carta invece è giocata di fronte viene incrementato il punteggio del giocatore con appositi controlli.
Questa funzione lancia l'eccezione ElementNotFound nel caso in cui la carta passata non esista nella matrice.

```java
	public boolean posizionaCarta(int nCarta, int nRiga, int nColonna, int nAngolo, boolean fronte) {
		Carta cartaScelta = mano.get(nCarta);
		
		if (campoPersonale.controlloPosizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			return false;
		}
		else {
			if(fronte == true) {
				if(cartaScelta instanceof CartaRisorsa) {
					this.punteggio += ((CartaRisorsa) cartaScelta).getPunti();
				}
				else if(cartaScelta instanceof CartaOro) {
					if(((CartaOro) cartaScelta).getCriterioPunti() == null) {
						this.punteggio += ((CartaOro) cartaScelta).getPunti();
					}
					else {
						Disegno criterio = ((CartaOro) cartaScelta).getCriterioPunti();
						int puntiCarta = ((CartaOro) cartaScelta).getPunti();
						//0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
						int contaDisegni [] = new int [7];
						campoPersonale.contaFigure(contaDisegni);
						
						switch(criterio) {
							case angoloSovrapposto:
								
								try {
									int countAngoli = 0;
									countAngoli = campoPersonale.angoliSovrapposti(cartaScelta);
									this.punteggio += (puntiCarta * countAngoli);
								}
								catch(ElementNotFoundException e) {
									return false;
								}
							break;
							case boccetta:
								this.punteggio += (puntiCarta * contaDisegni[4]);
							break;
							case piuma:
								this.punteggio += (puntiCarta * contaDisegni[5]);
							break;
							case pergamena:
								this.punteggio += (puntiCarta * contaDisegni[6]);
							break;
							default:
							break;
						}
					}
				}
			}
			mano.remove(nCarta);
			return true;
		}		
	}
```
<a id="posizionaCarta">Funzione per controllare il posizionamento di una carta nel campo da gioco: </a>
Per organizzare il campo da gioco si è usata una matrice 50x50, nella quale la carta starter viene messa in posizione 24x24.
Ogni qualvolta si desidera posizionare una carta bisogna inserire le coordinate (riga, colonna e numero angolo) della carta già presente nella matrice a cui si vuole attaccare quella che si ha in mano.
La funzione controlla che esista una carta nelle coordinate selezionate, che l'angolo scelto sia libero e che nella posizione in cui verrebbe inserita la carta non ce ne sia già presente un'altra. 
Nel caso in cui sia possibile posizionare la carta, viene controllato il suo tipo: se è carta risorsa la posiziona immediatamente, se invece è carta oro controlla che i criteri di posizionamento siano soddisfatti.
Le coordinate della carta da posizionare vengono calcolate automaticamente in base all'angolo scelto; esempio: se si è scelto l'angolo in alto a sinistra, le coordinate saranno: numRiga - 1 e numColonna - 1;
```java
public boolean controlloPosizionaCarta(Carta carta, int nRiga, int nColonna, int nAngolo) {
		if(campoPersonale[nRiga][nColonna]!=null && (nAngolo>=0 && nAngolo<8) && campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo)!=null) {
			if(campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).getOccupato()==false && controllaCarta(carta)==true) {
				switch (nAngolo) {
				case 0,4:
					if(campoPersonale[nRiga-1][nColonna-1] == null) {
						campoPersonale[nRiga-1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 1,5:
					if(campoPersonale[nRiga-1][nColonna+1] == null) {
						campoPersonale[nRiga-1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 2,6:
					if(campoPersonale[nRiga+1][nColonna+1] == null) {
						campoPersonale[nRiga+1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 3,7:
					if(campoPersonale[nRiga+1][nColonna-1] == null) {
						campoPersonale[nRiga+1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;						
				}				
			}			
		}
		return false;
	}
```
- Funzione per il controllo di una tipologia di carta obiettivo:
Controlla le due carte obiettivo del tipo: due carte in verticale, una sopra l'altra, con un dato colore e un'altra carta posta in diagonale rispetto a quella più alta con un altro colore.
Scorre tutta la matrice in cerca di una carta con lo stesso colore di quella in diagonale nella carta obiettivo; quando la trova controlla se ci sono due carte con il giusto colore disposte verticalmente in una colonna diagonale rispetto alla prima carta considerata.
Il set di interi serve per salvare le coordinate delle carte trovate in modo da evitare di conteggiare più volte le stesse carte per uno stesso obiettivo.
Esempio: due carte verticali in basso a destra rispetto alla prima carta; la seconda carta cercata avrà coordinate r+1 e c+1 e la terza r+2 e c+1.


```java
	public int obiettivoVerticaleInferiore(Colore colVerticale, Colore col1, int modColonna, int modRiga1, int modRiga2) {
		Set<Integer> index = new HashSet<Integer>();
		int tris=0;
		
		for(int i = 0; i < nRigheTabella; i++) {
			for(int j = 0; j < nColonneTabella; j++) {
				if(campoPersonale[i][j] != null) {
					if(campoPersonale[i][j].getColore().equals(col1)) {
						if(campoPersonale[i+modRiga1][j+modColonna]!=null && campoPersonale[i+modRiga2][j+modColonna]!=null) {
							if(campoPersonale[i+modRiga1][j+modColonna].getColore().equals(colVerticale) &&
								campoPersonale[i+modRiga2][j+modColonna].getColore().equals(colVerticale)) {
								if(controllaIndici(index, i, j, i+modRiga1, j+modColonna, i+modRiga2, j+modColonna)) {
									index.add(i);
									index.add(j);
									index.add(i+modRiga1);
									index.add(j+modColonna);
									index.add(i+modRiga2);
									index.add(j+modColonna);
									tris++;
								}
							}
						}
					}
				}
			}
		}
		return tris;
	}
```

## Autori

- **Lisa Rossi** - [Profilo GitHub](https://github.com/lizz34)
- **Raul Beltramelli** - [Profilo GitHub](https://github.com/raulino04)
- **Edoardo Pirola** - [Profilo GitHub](https://github.com/edoardopirola)
- **Francesco Pezzotta** - [Profilo GitHub](https://github.com/frapezzotta)


## Stato

Il gioco é attualmente completo e giocabile esculsivamente tramite linea di comando o console di Eclipse.
Si sta attualmente lavorando al completamento della parte grafica.

## Licenza

Questo progetto digitale è stato realizzato esclusivamente per scopi educativi e non commerciali. 
Codex Naturalis è un prodotto protetto da copyright, e tutti i diritti relativi al nome, ai contenuti e alle immagini di Codex Naturalis sono di proprietà dei legittimi proprietari. 
L'utilizzo di Codex Naturalis in questo progetto è inteso unicamente a fini didattici e non implica alcuna affiliazione o approvazione da parte dei proprietari del copyright.
