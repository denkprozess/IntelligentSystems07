Moin Klaas,

wir haben letzte Woche nicht abgegeben, daher hier erst mal ein paar Infos zu unserer Datenstruktur:

Im Prinzip haben wir die Definitionen aus den Folien nachmodelliert und nicht viel daran verändert.

Ein PlanningProblem besteht aus Resourcen, Produkten, Jobs, Hard- und SoftConstraints, Events, einer TargetFunction und einem Plan. Der Plan ist etwas komplexer.

Ein Plan beinhaltet eine Menge von geplanten Aufträgen (ScheduledJob). Ein ScheduledJob beinhaltet den Job mit geplanter Start- und Endzeit und einem Maschinenbelegungsplan (ConfigurationSchedule). Eine Configuration ist bei uns eine Maschinenbelegung. Ein ConfigurationSchedule beinhaltet dabei alle Configurations, die zum jeweiligen Job gehören.

Momentan passiert bei uns noch nix automatisch. Das heißt der Einstiegspunkt für dich ist die Runner-Klasse. Dort haben wir per Hand zuerst die Maschinen, dann die Produkte mit ihren Varianten und Operationen und abschließend die Jobs angelegt.

Für den fünften Übungszettel haben wir dann einen Plan angelegt, mit den soeben beschriebenen geplanten Jobs und den zugehörigen Maschinenbelegungen. Die Constraints werden dem PlanningProblem ebenfalls im Runner zugewiesen, ganz zum Schluss. Die Planung wird im Moment noch durch ein plumpes "planningProblem.print()" durchgeführt.

Im PlanningProblem existieren zwei Methoden (printHard(Soft)Constraints()), in denen die Überprüfung der Constraints angestoßen werden. Die zugehörigen Klassen haben einen "Constraint"-Suffix.

Die Constraints haben bei uns immer dieselbe Struktur. Sie verfügen über eine "isConstraintViolated()"-Methode und bekommen darin Jobs, Resourcen und den Plan übergeben. In der Methode prüfen sie dann, je nach Constraint, ob er verletzt wurde oder nicht und geben anschließend den passenden booleschen Wert zurück.

Die Ausgabe sollte sonst selbsterklärend sein. Ziemlich zum Schluss kannst du sehen, ob Constraints verletzt wurden (true = verletzt). Wir fügen ein paar Jobs bei, die die jeweiligen Constraints triggern, damit du nur noch umkommentieren musst zum Testen.

Viele Grüße
Gruppe 7