(ns dojo.problems-in-4clojure.096-beauty-is-symmetry)
;; https://4clojure.oxal.org/#/problem/96

(defn sym?
  [a b]
  (= a (reverse b)))

(defn sol [xs]
  (let [sc (second xs)
        third (last xs)]
    (= (second xs) (reverse (last xs)))))

(= (sol '(:a (:b nil nil) (:b nil nil))) true)

(= (sol '(:a (:b nil nil) nil)) false)

(= (sol '(:a (:b nil nil) (:c nil nil))) false)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [6 nil nil] nil]] nil]])
   false)
