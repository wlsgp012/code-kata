(ns dojo.problems-in-4clojure.126-through-the-looking-class)
;; https://4clojure.oxal.org/#/problem/126

(def sol (class (class ())))

(let [x sol]
  (and (= (class x) x) x))
