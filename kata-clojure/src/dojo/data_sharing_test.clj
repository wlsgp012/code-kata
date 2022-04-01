(ns dojo.data-sharing-test)

(def a '(1 2 3))

(def b (conj a 4))

(def c (rest b))

(= a c)

(identical? a c)

(= '(1 2 3) '(1 2 3))

(identical? '(1 2 3) '(1 2 3))

(identical? a '(1 2 3))
