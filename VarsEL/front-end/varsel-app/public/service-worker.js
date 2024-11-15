self.addEventListener('push', function (event) {
  const data = event.data.json()
  console.log('Push received:', data)

  const options = {
    body: data.body,
    icon: '/assets/c_icons/settings.svg',
    badge: '/assets/c_icons/settings.svg',
  }

  event.waitUntil(self.registration.showNotification(data.title, options))
})
