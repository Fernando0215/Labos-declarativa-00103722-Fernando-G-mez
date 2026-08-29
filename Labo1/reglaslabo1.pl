% ======================================================
% REGLAS - derivadas de hechos.pl
% ======================================================
:- [hechos].

% ------------------------------------------------------
% REGLA 1: personajes/2
% Verdadero si AMBOS argumentos son personajes conocidos.
% ------------------------------------------------------
personajes(X, Y) :-
    personaje(X),
    personaje(Y).

% ------------------------------------------------------
% REGLA 2: enemigos/2
% Verdadero si AMBOS argumentos son enemigos conocidos.
% ------------------------------------------------------
enemigos(X, Y) :-
    enemigo(X),
    enemigo(Y).

% ------------------------------------------------------
% REGLA 3: infectados/2
% Ya no hay hecho infectado_por/2, asi que la regla se apoya
% solo en enemigo/1: en este dominio todo enemigo definido
% (Ganados, Regeneradores) es por naturaleza un infectado por
% Las Plagas. Se mantiene separada de enemigos/2 para dejar
% claro el proposito semantico (y poder extenderla despues
% sin tocar enemigos/2).
% ------------------------------------------------------
infectados(X, Y) :-
    enemigo(X),
    enemigo(Y).

% ------------------------------------------------------
% REGLA 4: bien_armado/1
% Verdadero si el personaje porta pistola Y cuchillo (AND).
% ------------------------------------------------------
bien_armado(X) :-
    arma(X, pistola),
    arma(X, cuchillo).

% ------------------------------------------------------
% REGLA 5: rol_de_combate/1
% Verdadero si el personaje es agente O espia (OR).
% ------------------------------------------------------
rol_de_combate(X) :-
    dedicación(X, agente)
    ;
    dedicación(X, espia).

% ------------------------------------------------------
% REGLA 6: zona_alto_riesgo/1
% Verdadero si el lugar tiene dificultad alta O muy_alta (OR).
% ------------------------------------------------------
zona_alto_riesgo(L) :-
    dificultad(L, alta)
    ;
    dificultad(L, muy_alta).

% ------------------------------------------------------
% REGLA 7: mismo_lugar/2
% Verdadero si dos personajes DISTINTOS comparten ubicacion
% (AND + comparacion de desigualdad).
% ------------------------------------------------------
mismo_lugar(X, Y) :-
    ubicacion(X, L),
    ubicacion(Y, L),
    X \= Y.

% ------------------------------------------------------
% REGLA 8: mas_joven/2
% Verdadero si X tiene menor edad que Y (comparacion numerica).
% ------------------------------------------------------
mas_joven(X, Y) :-
    edad(X, EX),
    edad(Y, EY),
    EX < EY.

% ------------------------------------------------------
% REGLA 9: en_peligro/1
% Verdadero si un personaje esta en un lugar de alto riesgo.
% Reutiliza la regla 6 (composicion de reglas).
% ------------------------------------------------------
en_peligro(X) :-
    personaje(X),
    ubicacion(X, L),
    zona_alto_riesgo(L).
