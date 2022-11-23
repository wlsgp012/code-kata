(ns dojo.problems-in-4clojure.158-decurry)

(defn sol [f]
  (fn [& arg]
    (reduce #(%1 %2) f arg)))

(= 10 ((sol (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (+ a b c d))))))
       1 2 3 4))

(= 24 ((sol (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (* a b c d))))))
       1 2 3 4))

(= 25 ((sol (fn [a]
             (fn [b]
               (* a b))))
       5 5))

;; others
(let [decurry (fn decurry [f args]
                (if (first args) (decurry (f (first args)) (rest args)) f))]
  (fn [f] (fn [& args] (decurry f args))))
