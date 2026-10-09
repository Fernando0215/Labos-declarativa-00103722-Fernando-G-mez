pedir_digitos :-
    write('Ingresa un numero entero: '),
    read(N),
    guardarDigitos(N, L),
    write('La lista de digitos es: '),
    write(L),
    nl.

guardarDigitos(N, [N]) :- N < 10, !.
guardarDigitos(N, L) :-
    D is N mod 10,
    N1 is N // 10,
    guardarDigitos(N1, L1),
    concatenar(L1, [D], L).

concatenar([], L, L).
concatenar([H|T], L, [H|R]) :- concatenar(T, L, R).

