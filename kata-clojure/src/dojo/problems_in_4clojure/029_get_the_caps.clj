(ns dojo.problems-in-4clojure.029-get-the-caps)
;; https://4clojure.oxal.org/#/problem/29

(defn answer
  [s]
  (apply str (re-seq #"[A-Z]+" s)))

(= (answer "HeLlO, WoRlD!") "HLOWRD")
(empty? (answer "nothing"))
(= (answer "$#A(*&987Zf") "AZ")

;; others

(fn [s]
  (apply str (filter #(Character/isUpperCase %) s)))

#(.replaceAll % "[^A-Z]" "")
