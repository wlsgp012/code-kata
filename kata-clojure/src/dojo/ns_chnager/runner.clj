(ns dojo.ns-chnager.runner
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))


(defn get-nsname
  [ns]
  (s/replace (str (ns-name ns)) #"-" "_"))

(defn get-file-path
  [nsname]
  (str "src/"
       (s/join "/" (drop-last (s/split nsname #"\.")))))

(def targetPath "/Users/user/development/source/projects/code-kata/kata-clojure/src/dojo/problems_in_4clojure")

(defn- separate-filename
  [fn]
  (let [separator "."
        last-index (s/last-index-of fn separator)]
    (if (nil? last-index)
      fn
     [(subs fn 0 last-index) separator (subs fn (inc last-index) (count fn))])))

;; read files in directory
(defn get-files
  [path]
  (filter (fn [x] (and (.isFile x) (= "clj" (last (separate-filename (.getName x))))))
          (file-seq (io/file path))))

;; file name change + number padding 001 002

(defn padding
  [num count]
  (format (str "%0" count "d")
          (if (int? num)
            num
            (Integer/parseInt num))))

(defn post->pre
  [name sep]
  (let [splited (s/split name (re-pattern sep))]
    (str (padding (last splited) 3) sep (apply str (drop-last splited)))))

(defn change-name
  ([fullname] (change-name fullname "."))
  ([fullname sep]
   (let [splited (s/split fullname (re-pattern (str "\\" sep)))
         name (s/join sep (drop-last splited))
         extension (last splited)]
     (str (post->pre name "_") sep extension))))

  (defn rename-file
    [f]
    (let [name (.getName f)
          path (.getParent f)]
      ;;(println (str path (change-name name)))))
         (.renameTo f (io/file (str path "/" (change-name name))))))



;; ns change



(defn doit
  [path]
  (let [files (get-files path)]
    (doseq [f files]
      (rename-file f))))
