(ns dojo.design-pattern.06-iterator)

(def natural-numbers (iterate inc 1))

;; It's just mock code.
(deftype RedGreenBlackTree [elems]
  clojure.lang.Seqable
  (seq [self]
    ;; traverse element in needed order
    natural-numbers
    ))
