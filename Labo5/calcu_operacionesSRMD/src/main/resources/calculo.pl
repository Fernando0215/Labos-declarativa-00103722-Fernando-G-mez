% Suma
suma(A, B, R) :-
    number(A), number(B),
    R is A + B.

% Resta
resta(A, B, R) :-
    number(A), number(B),
    R is A - B.

% Multiplicacion
multiplicacion(A, B, R) :-
    number(A), number(B),
    R is A * B.

% Division (falla si B es 0)
division(A, B, R) :-
    number(A), number(B),
    B =\= 0,
    R is (A * 1.0) / B.