(ns dojo.problems-in-4clojure.078-reimplement-trampoline)
;; https://4clojure.oxal.org/#/problem/78

(defn sol
  ([f & args] (sol #(apply f args)))
  ([f] (let [result (f)]
         (if (fn? result)
           (recur result)
           result))))

(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop?(- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
     (sol triple 2))
   82)

(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (map (partial sol my-even?) (range 6)))
   [true false true false true false])

;; others
(fn [f & args]
  ((fn [f] (if (fn? f) (recur (f)) f)) (apply f args)))


(fn [f arg]
  (loop [g (f arg)]
    (if (fn? g)
      (recur (g))
      g)))
