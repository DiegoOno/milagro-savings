import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { SecurityService } from './security.service';

@Module({
  imports: [JwtModule.register({ secret: process.env.AUTH_SECRET })],
  providers: [SecurityService],
  exports: [SecurityService],
})
export class SecurityModule {}
