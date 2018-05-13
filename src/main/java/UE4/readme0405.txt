Moin Klaas,

wir haben letzte Woche nicht abgegeben, daher hier erst mal ein paar Infos zu unserer Datenstruktur:

Im Prinzip haben wir die Definitionen aus den Folien nachmodelliert und nicht viel daran ver�ndert.

Ein PlanningProblem besteht aus Resourcen, Produkten, Jobs, Hard- und SoftConstraints, Events, einer TargetFunction und einem Plan. Der Plan ist etwas komplexer.

Ein Plan beinhaltet eine Menge von geplanten Auftr�gen (ScheduledJob). Ein ScheduledJob beinhaltet den Job mit geplanter Start- und Endzeit und einem Maschinenbelegungsplan (ConfigurationSchedule). Eine Configuration ist bei uns eine Maschinenbelegung. Ein ConfigurationSchedule beinhaltet dabei alle Configurations, die zum jeweiligen Job geh�ren.

Momentan passiert bei uns noch nix automatisch. Das hei�t der Einstiegspunkt f�r dich ist die Runner-Klasse. Dort haben wir per Hand zuerst die Maschinen, dann die Produkte mit ihren Varianten und Operationen und abschlie�end die Jobs angelegt.

F�r den f�nften �bungszettel haben wir dann einen Plan angelegt, mit den soeben beschriebenen geplanten Jobs und den zugeh�rigen Maschinenbelegungen. Die Constraints werden dem PlanningProblem ebenfalls im Runner zugewiesen, ganz zum Schluss. Die Planung wird im Moment noch durch ein plumpes "planningProblem.print()" durchgef�hrt.

Im PlanningProblem existieren zwei Methoden (printHard(Soft)Constraints()), in denen die �berpr�fung der Constraints angesto�en werden. Die zugeh�rigen Klassen haben einen "Constraint"-Suffix.

Die Constraints haben bei uns immer dieselbe Struktur. Sie verf�gen �ber eine "isConstraintViolated()"-Methode und bekommen darin Jobs, Resourcen und den Plan �bergeben. In der Methode pr�fen sie dann, je nach Constraint, ob er verletzt wurde oder nicht und geben anschlie�end den passenden booleschen Wert zur�ck.

Die Ausgabe sollte sonst selbsterkl�rend sein. Ziemlich zum Schluss kannst du sehen, ob Constraints verletzt wurden (true = verletzt). Wir f�gen ein paar Jobs bei, die die jeweiligen Constraints triggern, damit du nur noch umkommentieren musst zum Testen.

Viele Gr��e
Gruppe 7