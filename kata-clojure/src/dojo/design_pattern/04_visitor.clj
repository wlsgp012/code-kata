(ns dojo.design-pattern.04-visitor)

(defn activity->pdf [item] (println "activity->pdf" (:content item)))
(defn activity->xml [item] (println "activity->xml" (:content item)))
(defn message->pdf [item] (println "message->pdf" (:content item)))
(defn message->xml [item] (println "message->xml" (:content item)))

(defmulti export
  (fn [item format] [(:type item) format]))

;; Message
{:type :message :content "Say what again!"}

;; Activity
{:type :activity :content "Quoting Ezekiel 25:17"}

(defmethod export [:activity :pdf] [item format]
  (activity->pdf item))

(defmethod export [:activity :xml] [item format]
  (activity->xml item))

(defmethod export [:message :pdf] [item format]
  (message->pdf item))

(defmethod export [:message :xml] [item format]
  (message->xml item))

(defmethod export :default [item format]
  (throw (IllegalArgumentException. "not supported")))

;; advanced features - adhoc hierarchies

(derive ::pdf ::format)
(derive ::xml ::format)

;;(defmethod export [:activity ::pdf])
;;(defmethod export [:activity ::xml])
;;(defmethod export [:activity ::format])
