import org.apache.http.HttpHeaders
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
  description "should return a reservation with name 'test'"

  request {
    url "/reservations/test"
    method GET()
  }

  response {
    status 200
    headers {
      header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
    }

    body(
            [id: 1L, name: "test"]
    )
  }
}
