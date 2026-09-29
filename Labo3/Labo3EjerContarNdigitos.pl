contar_digitos(0, 0).

contar_digitos(N, Cantidad) :-
    N > 0,
    N1 is N // 10,
    contar_digitos(N1, Cantidad1),
    Cantidad is Cantidad1 + 1.
