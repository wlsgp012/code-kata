(ns dojo.problems-in-4clojure.134-a-nil-key)
;; https://4clojure.oxal.org/#/problem/134

(defn sol [k m]
  (and (contains? m k) (nil? (m k))))

(true?  (sol :a {:a nil :b 2}))

(false? (sol :b {:a nil :b 2}))

(false? (sol :c {:a nil :b 2}))

;; others

(fn [k m] (nil? (k m :missing)))
