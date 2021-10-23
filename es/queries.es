/* lotto_result_store */
GET /_template/lotto_result_store_template
/*
{
    "lotto_result_store_template": {
        "order": 0,
        "index_patterns": [
            "lotto_result_store*"
        ],
        "settings": {},
        "mappings": {
            "properties": {
                "store_address1": {
                    "type": "keyword"
                },
                "method": {
                    "type": "keyword"
                },
                "store_address3": {
                    "type": "keyword"
                },
                "store_address2": {
                    "type": "keyword"
                },
                "store_address": {
                    "type": "keyword"
                },
                "turn": {
                    "type": "integer"
                },
                "store_map_id": {
                    "type": "keyword"
                },
                "store_phone": {
                    "type": "keyword"
                },
                "store_number": {
                    "type": "integer"
                },
                "rank": {
                    "type": "integer"
                },
                "store_name": {
                    "type": "keyword"
                },
                "row_id": {
                    "index": true,
                    "type": "keyword"
                },
                "store_location": {
                    "type": "geo_point"
                }
            }
        },
        "aliases": {
            "lotto_result_store_template": {}
        }
    }
}
*/

/* lotto_result_store store_address1 집계 */
GET /lotto_result_store/_search
{
    "from": 0,
    "size": 0,
    /* 1등 조건 */
    "query": {
        "bool": {
            "must": [
                {
                    "term": {
                        "rank": {
                            "value": 1
                        }
                    }
                }
            ]
        }
    },
    "aggregations": {
        "store_address1": {
            "terms": {
                "field": "store_address1",
                "size": 30
            }
        }
    }
}

/* lotto_result_store store_address1 기반 store_address2 집계 */
GET /lotto_result_store/_search
{
    "from": 0,
    "size": 0,
    "query": {
        "bool": {
            "must": [
                {
                    "term": {
                        "rank": {
                            "value": 1
                        }
                    }
                },
                {
                    /* 서울은 서울, 서울특별시 가 존재 */
                    "wildcard": {
                        "store_address1": {
                            "value": "서울*"
                        }
                    }
                }
            ]
        }
    },
    "aggregations": {
        "store_address2": {
            "terms": {
                "field": "store_address2",
                "size": 100
            }
        }
    }
}

/* lotto_result_store store_address1, store_address2 기반 store_address3 집계 */
GET /lotto_result_store/_search
{
    "from": 0,
    "size": 0,
    "query": {
        "bool": {
            "must": [
                {
                    "term": {
                        "rank": {
                            "value": 1
                        }
                    }
                },
                {
                    /* 서울은 서울, 서울특별시 가 존재 */
                    "wildcard": {
                        "store_address1": {
                            "value": "서울*"
                        }
                    }
                },
                {
                    "term": {
                        "store_address2": {
                            "value": "은평구"
                        }
                    }
                }
            ]
        }
    },
    "aggregations": {
        "store_address3": {
            "terms": {
                "field": "store_address3",
                "size": 300
            }
        }
    }
}

/* lotto_result_store store_address1, store_address2, store_address3 조건에 맞는 가게정보 */
GET /lotto_result_store/_search
{
    "from": 0,
    "size": 10000,
    "query": {
        "bool": {
            "must": [
                {
                    "term": {
                        "rank": {
                            "value": 1
                        }
                    }
                },
                {
                    /* 서울은 서울, 서울특별시 가 존재 */
                    "wildcard": {
                        "store_address1": {
                            "value": "서울*"
                        }
                    }
                },
                {
                    "term": {
                        "store_address2": {
                            "value": "은평구"
                        }
                    }
                },
                {
                    "term": {
                        "store_address3": {
                            "value": "불광동"
                        }
                    }
                }
            ]
        }
    }
}