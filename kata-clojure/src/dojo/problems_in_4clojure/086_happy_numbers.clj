(ns dojo.problems-in-4clojure.086-happy-numbers)
;; https://4clojure.oxal.org/#/problem/86

(defn sol
  [num]
  (loop [n num track #{}]
    (let [cal (apply + (map #(int (Math/pow (Character/getNumericValue %) 2)) (str n)))]
      (cond
        (= 1 cal) true
        (track cal) false
        :else (recur cal (conj track cal))))))

(= (sol 7) true)

(= (sol 986543210) true)

(= (sol 2) false)

(= (sol 3) false)
