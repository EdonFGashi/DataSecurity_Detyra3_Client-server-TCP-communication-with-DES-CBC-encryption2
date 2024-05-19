Universiteti i Prishtinës "Hasan Prishtina"

Fakulteti i Inxhinierisë Elektrike dhe Kompjuterike / Departamenti i Inxhinierisë Softuerike dhe Kompjuterike

Titulli i projektit: Implementimi i një programi për komunikim klient-server përmes TCP, duke enkriptuar mesazhet me DES/CBC.

Emrat e studentëve të grupit: Edon Gashi, Dreni Nimanaj, Eden Dobra dhe Elion Mehaj

Emri i profesorit: Prof. Dr. Blerim Rexha
Emri i asistentit: Msc. Mergim Hoti

Përshkrimi i projektit: ideja e këtij projekti është që të implementohet një program që bën të mundur komunikimin e përdoruesve brenda një rrjesti lokal të përbashkët
(LAN) përmes protokolit TCP, me arkitekturën klient-server. Qëllimi i programit është që mesazhet që shkëmbehen të enkriptohen me DES/CBC pas dërgimit nga dërguesi 
dhe të dekriptohen prap ato para leximit nga pranuesi. Ideja është që informacioni që kalon nëpër server të shfaqet në formatin e cipher text me qëllim që të jetë 
i pakuptueshëm nga personi që nuk e ka çelësin e dekriptimit. Meqë DES/CBC është algoritëm simetrik, atëherë do të përdoret vetëm një çelës për procesin e enkriptimit 
dhe dekriptimit. Meqë programi punon me threads është i aftë që të mundësojë komunikim mes 1 apo më shumë përdoruesve të kyçur në server duke ditur paraprakisht ip dhe 
portin që po përdoret për komunikim. Programi është i shkruar në gjuhën programuese Java ekzekutohet në cmd (command line). Programi ndahet në 3 foldera kryesorë: 
1. Server - që përmban klasën për komunikim me server dhe thread-at për përdoruesit
2. Client - që përmban klasat e klientit për komunikim dhe klasat për lexim dhe shkrim të mesazheve
3. DES - që përmban 2 metoda që bëjnë enkriptimin dhe dekriptimin e mesazheve

Rezultatet e projektit: Si fillim hapet një cmd ku startohet serveri duke thirrur klasën ChatServer (porti) dhe serveri do të presë sinjal në atë port.
Pastaj klientët e thërrasin klasën ChatClient (ip) (porti) ku lidhen me serverin, e vendosin emrin e tyre dhe mund të komunikojnë, lirshëm pasi që mesazhet në 
server do të shfaqen të enkriptuara. Mesazhi enkriptohet tek klienti që dërgon ndërsa dekriptohet tek klienti që pranon.@
